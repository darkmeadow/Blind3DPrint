package de.thn.print;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

public class DllController {
	
	autoHotKeyDll lib;

    public interface autoHotKeyDll extends Library {
        public void ahkExec(WString s);
        public void ahkdll(WString s,WString o,WString p);
        public void addFile(WString s, int a);
        public void ahktextdll(WString s,WString o,WString p);
        public Pointer ahkFunction(WString f,WString p1,WString p2,WString p3,WString p4,WString p5,WString p6,WString p7,WString p8,WString p9,WString p10);
    }

	public DllController() throws InterruptedException{

		this.lib = (autoHotKeyDll) Native.loadLibrary("AutoHotkey", autoHotKeyDll.class);

//		System.out.println("initialisation");
		lib.ahktextdll(new WString(""),new WString(""),new WString(""));
		Thread.sleep(100);
		lib.addFile(new WString(".\\commands.ahk"), 1);
		Thread.sleep(200);
	}
	
	public void runFunc(String s){
		lib.ahkFunction(new WString(s),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""));
	}
	
	public autoHotKeyDll getLib(){
		return this.lib;
	}
	
	public void run(WString s){
		lib.ahkExec(s);
	}
	
	public void runScript(WString s){
		lib.ahktextdll(s, s, s);
	}
	
}