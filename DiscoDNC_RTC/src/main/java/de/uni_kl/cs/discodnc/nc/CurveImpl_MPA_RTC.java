package de.uni_kl.cs.discodnc.nc;

import java.io.File;

import de.uni_kl.cs.discodnc.curves.CurvePwAffine;
import de.uni_kl.cs.discodnc.curves.LinearSegment;
import de.uni_kl.cs.discodnc.curves.mpa_rtc_pwaffine.Curve_MPARTC_PwAffine;
import de.uni_kl.cs.discodnc.curves.mpa_rtc_pwaffine.LinearSegment_MPARTC_PwAffine;
import de.uni_kl.cs.discodnc.minplus.MinPlus;
import de.uni_kl.cs.discodnc.minplus.MinPlus_MPA_RTC;
import de.uni_kl.cs.discodnc.nc.CalculatorConfig.CurveImpl;
import de.uni_kl.cs.discodnc.numbers.Num;

public enum CurveImpl_MPA_RTC implements CurveImpl {
	MPA_RTC;

	@Override
	public MinPlus getMinPlus() {
		return MinPlus_MPA_RTC.MinPlus_MPA_RTC;
	}

	@Override
	public CurvePwAffine getCurve() {
		return Curve_MPARTC_PwAffine.getFactory();
	}

	@Override
	public LinearSegment createLinearSegment(Num x, Num y, Num grad, boolean leftopen) {
		return new LinearSegment_MPARTC_PwAffine(x.doubleValue(), y.doubleValue(), grad.doubleValue());
	}

	@Override
	public LinearSegment createHorizontalLine(double y) {
		return new LinearSegment_MPARTC_PwAffine(0.0, y, 0.0);
	}
	
	@Override 
	public void checkDependencies() {
		String classpath = System.getProperty("java.class.path");
		for (String classpathEntry : classpath.split(File.pathSeparator)) {
			if (classpathEntry.contains("rtc.jar")) {
				return;
			}
		}
		throw new RuntimeException("rtc.jar cannot be found on the classpath!");
	}
}
