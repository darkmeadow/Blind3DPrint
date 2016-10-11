package de.thn.print;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class LiveVoiceRecognizerExample {



	public static void main(String[] args) throws Exception {

		Configuration configuration = new Configuration();
		DllController dll = new DllController();
		dll.run("msgbox,64,Test,Sprachaufnahme beginnt!");

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
				dll.run("msgbox,64,Test,Sprachaufnahme erfolgreich beendet!");
				recognizer.stopRecognition();
				System.exit(0);
			}
		}
	}
}