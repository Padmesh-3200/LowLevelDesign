package com.lld.structural_pattern;

//useCase :
//Assume you are working in a food delivery app and 
//where you collect the menu from all the hotels and combine them into single delivery platform.
//
//now, your application downloads and collects this data 
//from multiple sources in XML format and using those for displaying 
//
//now you are interested to use the 3rd party lib which supports the ui customisation
//and there is one check , this third party lib works with the data in Json Format.
//
//so U may want to change the library to work with xml but this might break 
//some existing code that relies on the lib
//
//and worse u might not have access to the lib source code.

//Now adapter pattern places here

interface MultiRestoApp{
	public void displayMenu(XmlData xmlData);
    public void displayRecommendation(XmlData xmlData);
}

class XmlData{
	// Assume this class is responsible for serving Xml data
}
class JsonData{
	// Assume this class is responsible for serving json data
}
class MultiRestoAppImplementation implements MultiRestoApp{

	@Override
	public void displayMenu(XmlData xmlData) {
		// Displays menus ui comp using XML data
        System.out.println("Displaying Menus using XML data...");	
	}
	
	@Override
	public void displayRecommendation(XmlData xmlData) {
		// Displays recommendations ui comp  using XML data
        System.out.println("Displaying Recommendations using XML data...");
	}
	
}

//3rd party lib class dont have access here to code
class CustomizationUiService {
	
	public void displayMenu(JsonData json) {
	//	uses Json Object to Diplay menu ui comp
	}

	
	public void displayRecommendation(JsonData  json) {
    //   uses Json Object to load recommendations  Ui comp
	}
}

class CustomizationUiServiceAdapter implements MultiRestoApp {

    private final CustomizationUiService customUIService;

    public CustomizationUiServiceAdapter() {
        customUIService = new CustomizationUiService();
    }

    @Override
    public void displayMenu(XmlData xmlData) {
        JsonData jsonData = convertXmlToJson(xmlData);
        System.out.println("Displaying 3rd Party Menus using converted JSON data...");
        customUIService.displayMenu(jsonData);
    }

    @Override
    public void displayRecommendation(XmlData xmlData) {
        JsonData jsonData = convertXmlToJson(xmlData);
        System.out.println("Displaying 3rd Party Recommendations using converted JSON data...");
        customUIService.displayRecommendation(jsonData);
    }

    private JsonData convertXmlToJson(XmlData xmlData) {
        // Converts XmlData to JsonData and return it
        System.out.println("Converting XML to JSON...");
        return new JsonData();
    }

}

// So now for an any incompatible object type for an application, may changed to its type and adapt to its way  

public class AdapterPatternClient {

	public static void main(String[] args) {

		XmlData myData = new XmlData();

		// Old UI
		MultiRestoApp multiRestoApp = new MultiRestoAppImplementation();
		multiRestoApp.displayMenu(myData);
		multiRestoApp.displayRecommendation(myData);

		System.out.println("==========================================");

		// New UI
		MultiRestoApp adapter = new CustomizationUiServiceAdapter();
		adapter.displayMenu(myData);
		adapter.displayRecommendation(myData);

	}

}