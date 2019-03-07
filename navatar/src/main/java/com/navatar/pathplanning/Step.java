package com.navatar.pathplanning;

import com.navatar.maps.Landmark;
import com.navatar.maps.particles.ParticleState;

/**
 * A single step within the path
 *
 * @author Kevin Glass
 */
public class Step {

    private String direction;
    private ParticleState particlestate;
    private Landmark landmark;
    private boolean followLeft;

    /**
     * Create a new step
     *
     * @param inlandmark the goal {@link Landmark} for this step
     * @param pState the {@link ParticleState} of the step
     */
    public Step(Landmark inlandmark, ParticleState pState) {
        this.direction = "";
        this.particlestate = pState;
        this.landmark = inlandmark;
    }

    public Landmark getlandmark() {
        return this.landmark;
    }

    public void setlandmark(Landmark inlandmark) {
        this.landmark = inlandmark;
    }

    public String getDirectionString() {
        return this.direction;
    }

    public void setDirectionString(String dirString) {
        this.direction = dirString;
    }

    public ParticleState getParticleState() {
        return this.particlestate;
    }

    public void setParticleState(ParticleState pState) {
        this.particlestate = pState;
    }

    /**
     * @see Object#hashCode()
     */
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + this.getlandmark().hashCode();
        return hash;
    }

    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        if (other instanceof Step) {
            Step o = (Step) other;
            return (o.particlestate.getX() == this.particlestate.getX())
                    && (o.particlestate.getY() == this.particlestate.getY());
        }
        return false;
    }

    @Override
    public String toString() {
        return getDirectionString();
    }

    public boolean isFollowLeft() {
        return followLeft;
    }

    public void setFollowLeft(boolean followLeft) {
        this.followLeft = followLeft;
    }
}
