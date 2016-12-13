package de.thn.print;

import com.sun.jna.WString;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class LiveVoiceRecognizerExample {
	

	public static void main(String[] args) throws Exception {

		Configuration configuration = new Configuration();
		DllController dll = new DllController();
		TTS tts = new TTS(dll);

		tts.say("Sprachaufnahme beginnt. Sagen Sie öffnen, um eine Datei zum Drucken zu laden");
//		configuration
//		.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration
		.setAcousticModelPath("resource:/voxforge/model");
//		configuration
//		.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration
		.setDictionaryPath("resource:/voxforge/etc/voxforge.dic");
//		configuration
//		.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		configuration
		.setLanguageModelPath("resource:/voxforge/etc/voxforge.lm.dmp");

		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(true);
		SpeechResult result = recognizer.getResult();
		// Pause recognition process. It can be resumed then with startRecognition(false).
		
		while (true){
			result = recognizer.getResult();
			String res = result.getHypothesis();
			System.out.println(res);
			if (res.equals("ENDE")){
				dll.run(new WString("msgbox,64,Test,Sprachaufnahme erfolgreich beendet!"));
				recognizer.stopRecognition();
				System.exit(0);
			}

			switch (res){
			case "VERBINDEN":
//				dll.run(new WString("winactivate, Repetier-Host `n"
//						+ "controlclick, X39 Y75"));
				dll.runFunc("connect");
				break;
			case "Ã–FFNEN":
//				dll.run(new WString("winactivate, Repetier-Host `n"
//						+ "send, ^o"));
				dll.runFunc("open");
				break;
			case "BERECHNEN":
//				dll.run(new WString("winactivate, Repetier-Host `n"
//						+ "controlclick, Slice mit CuraEngine"));
				dll.runFunc("slice");
				break;
			case "DRUCKEN":
//				dll.run(new WString("winactivate, Repetier-Host `n"
//						+ "controlclick, Drucken"));
				dll.runFunc("print");
				break;
			default:
				break;
			}
			
		}
	}
}