package DesignView.Components;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import DesignView.Frames.MainMenu;


public class AudioPlayer {

	public enum DefinedAudios{
		CLICK_Sound, FAST_Sound, BEACH_Sound, SINGLETICK_Sound, EXPLODE_Sound, BULB_Sound
	}

	private static URL getAudioResource(String res){
		return MainMenu.class.getResource("/DesignView/Sounds/" + res + ".wav");
	}
	
	public static void playAudioByResourceName(DefinedAudios audio) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		URL audioUrl;
		switch(audio){
		
			case CLICK_Sound:
				audioUrl = getAudioResource("Sand");
				break;
				
			case FAST_Sound:
				audioUrl = getAudioResource("Fast");
				break;
				
			case BEACH_Sound:
				audioUrl = getAudioResource("Beach");
				break;
				
			case SINGLETICK_Sound:
				audioUrl = getAudioResource("SingleTick");
				break;
				
			case EXPLODE_Sound:
				audioUrl = getAudioResource("Explode");
				break;
				
			case BULB_Sound:
				audioUrl = getAudioResource("Sand");
				break;
				
			default:
				audioUrl = getAudioResource("Sand");
				break;
		}
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioUrl);
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}
}
