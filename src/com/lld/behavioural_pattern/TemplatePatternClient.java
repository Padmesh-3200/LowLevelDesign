package com.lld.behavioural_pattern;



//use case : loading the Game initial page 
abstract class BaseGameLoader {

    public void load() {
        byte[] data = loadLocalData();
        createObjects(data);
        downloadAdditionalFiles();
        cleanTempFiles();
        initializeProfiles();
    }

    // these below 4 methods may vary to objects , in our case games
    abstract byte[] loadLocalData();

    abstract void createObjects(byte[] data);

    abstract void downloadAdditionalFiles();

    abstract void initializeProfiles();

    // but this part is common for all the generalized classes(child) , 
    // in our case cleaning temp files for all games may have the same process
    protected void cleanTempFiles() {
        System.out.println("Cleaning temporary files...");
        // Some Common Code...
    }

}
class RealTimeGame1Loader extends BaseGameLoader {

    @Override
    public byte[] loadLocalData() {
        System.out.println("Loading RealTimeGame1Loader files...");
        // Some RealTimeGame1Loader Code...
        return new byte[0];
    }

    @Override
    public void createObjects(byte[] data) {
        System.out.println("Creating RealTimeGame1Loader objects...");
        // Some RealTimeGame1Loader Code...
    }

    @Override
    public void downloadAdditionalFiles() {
        System.out.println("Downloading RealTimeGame1Loader sounds...");
        // Some RealTimeGame1Loader Code...
    }

    @Override
    public void initializeProfiles() {
        System.out.println("Loading RealTimeGame1Loader profiles...");
        // Some RealTimeGame1Loader Code...
    }

}
class RealTimeGame2Loader extends BaseGameLoader {

    @Override
    public byte[] loadLocalData() {
        System.out.println("Loading local RealTimeGame2Loader files...");
        // Some RealTimeGame2Loader Code...
        return new byte[0];
    }

    @Override
    public void createObjects(byte[] data) {
        System.out.println("Creating RealTimeGame2Loader objects...");
        // Some RealTimeGame2Loader Code...
    }

    @Override
    public void downloadAdditionalFiles() {
        System.out.println("Downloading RealTimeGame2Loader sounds...");
        // Some RealTimeGame2Loader Code...
    }

    @Override
    public void initializeProfiles() {
        System.out.println("Loading RealTimeGame2Loader profiles...");
        // Some RealTimeGame2Loader Code...
    }

}
public class TemplatePatternClient{
	public static void main(String...strings) {
		BaseGameLoader wowLoader = new RealTimeGame1Loader();
        wowLoader.load();

        System.out.println("==========================================");

        BaseGameLoader diabloLoader = new RealTimeGame2Loader();
        diabloLoader.load();

	}
}