package ben_10;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class MyAudioPlayer {
	
	public Map<String, AudioInputStream> streamMap = new HashMap<String, AudioInputStream>();
//	Clip audioClip;
	public MyAudioPlayer() {
		try {
//			audioClip = AudioSystem.getClip();
			streamMap.put("bgm",AudioSystem.getAudioInputStream(getClass().getResource("/audio/bgm.wav")));
			streamMap.put("jump",AudioSystem.getAudioInputStream(getClass().getResource("/audio/jump.wav")));
			streamMap.put("move",AudioSystem.getAudioInputStream(getClass().getResource("/audio/move.wav")));
		    streamMap.put("fire",AudioSystem.getAudioInputStream(getClass().getResource("/audio/fire.wav")));
		    streamMap.put("water",AudioSystem.getAudioInputStream(getClass().getResource("/audio/water.wav")));
		    streamMap.put("kill",AudioSystem.getAudioInputStream(getClass().getResource("/audio/kill.wav")));
		    streamMap.put("hurt",AudioSystem.getAudioInputStream(getClass().getResource("/audio/hurt.wav")));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play(String fileName, boolean loop) {
		try {
			AudioInputStream s = streamMap.get(fileName);
			Clip audioClip;
			audioClip = AudioSystem.getClip();
			audioClip.open(s);
			if(loop)
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				audioClip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
