package Controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

class VoiceController {
    private Voice voice;


    public VoiceController() {
        VoiceManager voiceManager;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice("kevin16");
        voice.allocate();

    }
    public void Speak(String text) {
        voice.speak(text);
        voice.deallocate();
    }
}
