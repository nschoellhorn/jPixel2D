package de.mrpixeldream.engine.jpixel2d.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

public class SoundLib
{
	Hashtable<String, AudioClip> sounds;
	Vector<AudioClip> looping_clips;
	
	public SoundLib()
	{
		sounds = new Hashtable<String, AudioClip>();
		looping_clips = new Vector<AudioClip>();
	}
	
	public void loadSound(String name, String path)
	{
		if (sounds.containsKey(name))
		{
			return;
		}
		
		URL sound_url = getClass().getResource(path);
		sounds.put(name, Applet.newAudioClip(sound_url));
	}
	
	public void playSound(String name)
	{
		AudioClip audio = sounds.get(name);
		audio.play();
	}
	
	public void loopSound(String name)
	{
		AudioClip audio = sounds.get(name);
		looping_clips.add(audio);
		audio.loop();
	}
	
	public void stopLoops()
	{
		for (AudioClip c : looping_clips)
		{
			c.stop();
		}
	}
}