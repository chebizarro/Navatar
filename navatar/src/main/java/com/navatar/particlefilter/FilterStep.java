package com.navatar.particlefilter;

import java.util.ArrayList;
import java.util.List;

public class FilterStep {

    private long mTime;

    private double mMeanX;

    private double mMeanY;

    private List<Filter> mFilters = new ArrayList<>();

    public FilterStep(long time, double meanX, double meanY) {
        mTime = time;
        mMeanX = meanX;
        mMeanY = meanY;
    }

    public void addFilter(Filter filter) {
        mFilters.add(filter);
    }

    public List<Filter> getFilters() {
        return mFilters;
    }

    public void addFilter(double step, double[] vars) {
        addFilter(new Filter(step, vars));
    }

    public class Filter {

        private double mStep;

        private double mMeanX;

        private double mMeanY;

        private double mCovX;

        private double mCovXY;

        private double mCovY;

        public Filter(double step, double[] vars) {
            mStep = step;
            mMeanX = vars[0];
            mMeanY = vars[1];
            mCovX = vars[2];
            mCovXY = vars[3];
            mCovY = vars[4];
        }


    }
}
