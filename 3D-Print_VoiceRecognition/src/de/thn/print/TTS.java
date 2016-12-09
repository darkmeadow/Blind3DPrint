package de.thn.print;

public class TTS {
	
	public TTS(DllController dll){
		this.dll = dll;
	}
	
DllController dll;	

	public void say(String text){
		 dll.run(String.format("ComObjCreate(\"SAPI.SpVoice\").Speak(\"%s\")", text));

	}
	
}
