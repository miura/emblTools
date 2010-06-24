package emblcmci;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.GenericDialog;
import ij.measure.Calibration;
import ij.plugin.Duplicator;
import ij.plugin.HyperStackConverter;

/** Extract 3D image stack from 4D sequence
 * 
 * @author Kota Miura
 *
 */
public class Extractfrom4D {

	private int current3DstackStart = 0; 
	private int current3DstackEnd = 1; 
	
	public void run(int extractdimension){
		ImagePlus imp = WindowManager.getCurrentImage();
		if (imp.getNDimensions() < 4) {
			IJ.error("Check Image Properties: this stack has no t-dimension");
			return;
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
		
		Duplicator dup = new Duplicator();
		ImagePlus imp2 = dup.run(imp, current3DstackStart, current3DstackEnd);
		if (extractdimension == 4){
			//HyperStackConverter hyp = new HyperStackConverter();
			//hyp.shuffle();
			imp2.setDimensions(nChannels, nSlices, newframes);
			imp2.setOpenAsHyperStack(true);
		}
		imp2.show();
	}
	
	void getStartAndEnd3D(int currentFrame, int nSlices, int nChannels){
		current3DstackStart = (currentFrame-1)*nSlices*nChannels +1; 
		current3DstackEnd = currentFrame*nSlices*nChannels; 		
	}
	int getStartAndEnd4D(int nFrames, int nSlices, int nChannels){
		GenericDialog gd = new GenericDialog("Time Frame Range");
		gd.addNumericField("Start t-Frame (>=1) :", 1, 0);
		gd.addNumericField("End t-Frame (<"+ nFrames+") :", nFrames, 0);
		gd.showDialog();
		if (gd.wasCanceled()) 
			return -1;
		int startframe = (int) gd.getNextNumber();
		int endframe = (int) gd.getNextNumber();
		if ((startframe<1) || (endframe>nFrames))
				return -1;
		if (startframe > endframe) return -1;
		current3DstackStart = (startframe-1)*nSlices*nChannels +1;
		current3DstackEnd = endframe*nSlices*nChannels; 
		return (endframe - startframe + 1);
	}
}


