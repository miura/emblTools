package de.embl.cmci;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.GenericDialog;
import ij.measure.Calibration;
import ij.plugin.Concatenator;
import ij.plugin.Duplicator;
import ij.plugin.HyperStackConverter;

/** Extract 3D image stack from 4D sequence
 * 
 * @author Kota Miura
 *
 *
 * example script (jython)
 *
	# extracting single time frame (the first time point)

	from emblcmci import Extractfrom4D
	e4d = Extractfrom4D()
	e4d.setGstarttimepoint(1)
	imp = e4d.coreheadless(IJ.getImage(), 3)
	imp.show()
 *
 *
 */
public class Extractfrom4D {

	private int current3DstackStart = 0; 
	private int current3DstackEnd = 1; 
	private int currentIncrement = 1;
	private int Gstartframe = 1;
	private int Gendframe = 2;
	private int Gstarttimepoint;
	private int Gendtimepoint;
	private int Gincrement;
	public void run(int extractdimension){
		ImagePlus imp = WindowManager.getCurrentImage();
		ImagePlus imp2 = core(imp, extractdimension);
		imp2.show();
	}
		
	public ImagePlus core(ImagePlus imp, int extractdimension){
		if (imp.getNDimensions() < 4) {
			IJ.error("Check Image Properties: this stack has no t-dimension");
			return null;
		}
		Calibration cal = new Calibration(imp);
		int nSlices = imp.getNSlices();
		int nFrames = imp.getNFrames();
		int nChannels = imp.getNChannels();
		
		int newframes = 1; 
		if (extractdimension == 3) {
			getStartAndEnd3D(imp.getFrame(), nSlices, nChannels);
		} else {	//extract a range of 4D stack
			newframes = getStartAndEnd4D(nFrames, nSlices, nChannels);
		}
		ImagePlus imp2 = null;
		Duplicator dup = new Duplicator();
		if (currentIncrement == 1)
			imp2 = dup.run(imp, current3DstackStart, current3DstackEnd);
		else {
			ImagePlus imptmp = null;
			Concatenator ct = new Concatenator();
			for (int i = Gstartframe; i <= Gendframe; i ++) {				
				current3DstackStart = (i-1)*nSlices*nChannels +1; 
				current3DstackEnd = i*nSlices*nChannels;
				if (i == Gstartframe){
					imp2 = dup.run(imp, current3DstackStart, current3DstackEnd);
				} else {
					if (((i - Gstartframe) % currentIncrement) == 0){
						imptmp = dup.run(imp, current3DstackStart, current3DstackEnd);
						imp2 = ct.concatenate(imp2, imptmp, false);
					}
				}
			}	
		}
			
		if (extractdimension == 4){
			//HyperStackConverter hyp = new HyperStackConverter();
			//hyp.shuffle();
			imp2.setDimensions(nChannels, nSlices, newframes);
			imp2.setOpenAsHyperStack(true);
		}
		return imp2;
	}
	
	/** sets field values for starting and ending index in the stack
	 * according to the given frame number
	 * 
	 * @param currentFrame currently positioned frame number
	 * @param nSlices	number of slices per frame
	 * @param nChannels	number of channels per frame
	 */
	void getStartAndEnd3D(int currentFrame, int nSlices, int nChannels){
		current3DstackStart = (currentFrame-1)*nSlices*nChannels +1; 
		current3DstackEnd = currentFrame*nSlices*nChannels; 		
	}
	
	int getStartAndEnd4D(int nFrames, int nSlices, int nChannels){
		GenericDialog gd = new GenericDialog("Time Frame Range");
		gd.addNumericField("Start t-Frame (>=1) :", 1, 0);
		gd.addNumericField("End t-Frame (<"+ nFrames+") :", nFrames, 0);
		gd.addNumericField("Increment ", currentIncrement, 0);
		gd.showDialog();
		if (gd.wasCanceled()) 
			return -1;
		int startframe = (int) gd.getNextNumber();
		int endframe = (int) gd.getNextNumber();
		int increment = (int) gd.getNextNumber();
		
		int resultframes = getStartAndEnd4Dcore(
				nFrames, nSlices, nChannels, startframe, endframe, increment);
		return resultframes;
	}

	/** without GUI, suppose that the startpoint and endpoint are
	 * set by setters. for headless. 
	 * 
	 * @param nFrames
	 * @param nSlices
	 * @param nChannels
	 * @return
	 */
	int StartAndEnd4D(int nFrames, int nSlices, int nChannels){
		int startframe = Gstarttimepoint;
		int endframe = Gendtimepoint;
		int increment = Gincrement;
		int resultframes = getStartAndEnd4Dcore(
				nFrames, nSlices, nChannels, startframe, endframe, increment);
		return resultframes;
	}
	
	int getStartAndEnd4Dcore(
			int nFrames, int nSlices, int nChannels,
			int startframe, int endframe, int increment){		

		if ((startframe<1) || (endframe>nFrames))
				return -1;
		if (startframe > endframe) return -1;
		current3DstackStart = (startframe-1)*nSlices*nChannels +1;
		current3DstackEnd = endframe*nSlices*nChannels;
		currentIncrement = increment;
		Gstartframe = startframe;
		Gendframe = endframe;		
		int resultframes = (int) Math.ceil((endframe - startframe + 1)/increment);
		return resultframes;
	}

	// start and end t frame must be set by setters. 
	public ImagePlus coreheadless(ImagePlus imp, int extractdimension){
		if (imp.getNDimensions() < 4) {
			IJ.error("Check Image Properties: this stack has no t-dimension");
			return null;
		}
		Calibration cal = new Calibration(imp);
		int nSlices = imp.getNSlices();
		int nFrames = imp.getNFrames();
		int nChannels = imp.getNChannels();
		
		int newframes = 1; 
		if (extractdimension == 3) {
//			getStartAndEnd3D(imp.getFrame(), nSlices, nChannels);
			getStartAndEnd3D(Gstarttimepoint, nSlices, nChannels);			
		} else {	//extract a range of 4D stack
			newframes = StartAndEnd4D(nFrames, nSlices, nChannels);
		}
		ImagePlus imp2 = null;
		Duplicator dup = new Duplicator();
		if (currentIncrement == 1)
			imp2 = dup.run(imp, current3DstackStart, current3DstackEnd);
		else {
			ImagePlus imptmp = null;
			Concatenator ct = new Concatenator();
			for (int i = Gstartframe; i <= Gendframe; i ++) {				
				current3DstackStart = (i-1)*nSlices*nChannels +1; 
				current3DstackEnd = i*nSlices*nChannels;
				if (i == Gstartframe){
					imp2 = dup.run(imp, current3DstackStart, current3DstackEnd);
				} else {
					if (((i - Gstartframe) % currentIncrement) == 0){
						imptmp = dup.run(imp, current3DstackStart, current3DstackEnd);
						imp2 = ct.concatenate(imp2, imptmp, false);
					}
				}
			}	
		}
		if (extractdimension == 4){
			//HyperStackConverter hyp = new HyperStackConverter();
			//hyp.shuffle();
			imp2.setDimensions(nChannels, nSlices, newframes);
			imp2.setOpenAsHyperStack(true);
		}
		return imp2;
	}

	/**
	 * @return the currentIncrement
	 */
	public int getCurrentIncrement() {
		return currentIncrement;
	}

	/**
	 * @param currentIncrement the currentIncrement to set
	 */
	public void setCurrentIncrement(int currentIncrement) {
		this.currentIncrement = currentIncrement;
	}

	/**
	 * @return the gstarttimepoint
	 */
	public int getGstarttimepoint() {
		return Gstarttimepoint;
	}

	/**
	 * @param gstarttimepoint the gstarttimepoint to set
	 */
	public void setGstarttimepoint(int gstarttimepoint) {
		Gstarttimepoint = gstarttimepoint;
	}

	/**
	 * @return the gendtimepoint
	 */
	public int getGendtimepoint() {
		return Gendtimepoint;
	}

	/**
	 * @param gendtimepoint the gendtimepoint to set
	 */
	public void setGendtimepoint(int gendtimepoint) {
		Gendtimepoint = gendtimepoint;
	}

	/**
	 * @return the gincrement
	 */
	public int getGincrement() {
		return Gincrement;
	}

	/**
	 * @param gincrement the gincrement to set
	 */
	public void setGincrement(int gincrement) {
		Gincrement = gincrement;
	}
}



