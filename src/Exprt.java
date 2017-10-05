/**
 * Created by gonah on 10/5/2017.
 */import duke.*;
import org.apache.commons.csv.*;
public class Exprt {



    public class Export {

	public void readFood() {
		FileResource fr = new FileResource();
		CSVParser parser=fr.getCSVParser();
		for (CSVRecord record : parser){
			System.out.print(record.get("Name") + " ");
			System.out.print(record.get("Favorite Color") + " ");
			System.out.println(record.get("Favorite Food"));
		}
	}

	cpublic void listExporters(CSVParser parser, String exportOfInterest) {
		for(CSVRecord record :parser) {
			String export=record.get("Exports");
			if(export.contains(exportOfInterest)) {
				String country = record.get("country");
				System.out.println(country);
			}
		}

	}

	public  void whoExportsCoffee() {
		FileResource fr=new FilerResource();
		CSVParser parser=fr.getCSVParser();
		listExporters(parser,"coffee");
	}


        public void tester () {
            FileResource fr=new FileResource();
            CSVParser parser=fr.getCSVParser();// should write in main method
        }

        public void countryInfo(CSVParser parser, String countryName) {
            for (CSVRecord record: parser) {
                String country=record.get("Country");

                if (country.contains(countryName)   ) {

                    String exports=record.get("Exports");         //checking country information
                    String Money=record.get("Value");

                    System.out.println(country+":");
                    System.out.println(exports+":");
                    System.out.println(money+":");
                }
                else
                    System.out.println("Not Found");


            }
        }

        public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2 ) {

            for (CSVRecord record: parser) {
                String item=record.get("Exports");      //bot item checking


                if(item.contains(exportItem1) && item.contains(exportItem2)) {
                    String country=record.get("Country");
                    System.out.println(country);
                }
            }


        }


        public int numberofExporters(CSVParser parser, String exportItem) {
            int count=0;
            for (CSVRecord record: parser) {
                String item=record.get("Exports");      //number of countries export the item

                if(item.contains(exportItem)) {
                    count++;
                }

            }
            return count;
        }

        public void bigExporter(CSVParser parser, String amount) {

            int len= amount.length();
            for(CSVRecord record :parser) {
                String value=record.get("Value");             // Bigexporters
                if(value.length()>len) {
                    String country=record.get("Country");
                    System.out.println(country+"  ");
                    System.out.println(value);

                }
            }


        }

        public static void main(String[] args) {

        }

    }
}
