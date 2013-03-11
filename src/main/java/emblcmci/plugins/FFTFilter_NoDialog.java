package emblcmci.plugins;

import emblcmci.FFTFilter_NoGenDia;
import ij.ImagePlus;
import ij.WindowManager;
import ij.plugin.PlugIn;

/* configured for menu by plugin.config*/
public class FFTFilter_NoDialog implements PlugIn {
	ImagePlus imp;
	@Override
	public void run(String arg) {
		// TODO Auto-generated method stub
		this.imp = WindowManager.getCurrentImage();
		FFTFilter_NoGenDia fft = new FFTFilter_NoGenDia();
		fft.core(imp);
	}

}


