package com.navatar.common;

/**
 * @author Chris Daley
 */
public interface TextToSpeechProvider {

    void speak(String text);

    void speak(int resource);

}
