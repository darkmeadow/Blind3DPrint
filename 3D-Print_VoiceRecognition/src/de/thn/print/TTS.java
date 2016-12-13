package de.thn.print;

import com.sun.jna.WString;

public class TTS {
	
	public TTS(DllController dll){
		this.dll = dll;
	}
	
DllController dll;	

	public void say(String text){
		 dll.run(new WString(String.format("ComObjCreate(\"SAPI.SpVoice\").Speak(\"%s\")", text)));

	}
	
}
