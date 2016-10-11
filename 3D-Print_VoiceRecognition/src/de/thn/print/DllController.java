package de.thn.print;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class DllController {
	
	autoHotKeyDll lib;

	public interface autoHotKeyDll extends Library {
		public void ahkExec(char[] s);
		public void ahktextdll(char[] s);
	}

	public DllController() throws InterruptedException{

		this.lib = (autoHotKeyDll) Native.loadLibrary("AutoHotkey", autoHotKeyDll.class);

//		System.out.println("initialisation");
		lib.ahktextdll("#Persistent".toCharArray());
//		Thread.sleep(100);
//
//		System.out.println("displaying cbBox");
//		lib.ahkExec("msgbox,,Hellow!,Hellow World!".toCharArray());
	}
	
	public autoHotKeyDll getLib(){
		return this.lib;
	}
	
	public void run(String s){
		lib.ahkExec(s.toCharArray());
	}
	
}