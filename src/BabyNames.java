
    import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

    public class BabyNames {

        private <T> void pl(T t) {System.out.println(t);}

        public void totalBirths(FileResource fr) {

            CSVParser parser = fr.getCSVParser(false);

            int totalBirths = 0;
            int totalBoys = 0;
            int totalGirls = 0;

            for (CSVRecord record : parser) {

                int births =  Integer.parseInt(record.get(2));
                totalBirths += births;

                if (record.get(1).equals("M")) totalBoys += births;
                else totalGirls += births;

            }

            pl("Total births: " + totalBirths);
            pl("Girls births: " + totalGirls);
            pl("Boys births: " + totalBoys);
        }
        public void totalBirths() {

            FileResource fr = new FileResource();
            totalBirths(fr);

        }
        private CSVParser parserFactory(int year) {

            final String path =  "C:/Users/pd/Documents/Coursera/duke-java-1/BabyNames/data/us_babynames_by_year/";
            String fileName = path+"yob"+year+".csv";
            FileResource fr = new FileResource(fileName);
            return fr.getCSVParser(false);

        }
        private CSVParser parserFactory(File file) {

            FileResource fr = new FileResource(file);
            return fr.getCSVParser(false);

        }
        public int getRank(int year, String name, String gender) {

            CSVParser parser = parserFactory(year);
            return getRank(parser, name, gender);

        }
        public int getRank(CSVParser parser, String name, String gender) {

            int rank = 0;

            for (CSVRecord record : parser) {

                if (record.get(1).equals(gender)) {

                    rank++;
                    // stop counting rank if name is found
                    if (record.get(0).equals(name)) {

                        return rank;
                    }
                }
            }

            return -1;
        }

        public String getName(int year, int rank, String gender) {

            CSVParser parser = parserFactory(year);

            for (CSVRecord record : parser) {

                // skip if wrong gender
                if (!record.get(1).equals(gender)) continue;

                // return name if found matching rank
                String name = record.get(0);
                if (rank == getRank(year, name, gender)) return name;

            }

            // return if rank not found
            return "NO NAME";
        }

        public String whatIsNameInYear(String name, int year, int newYear, String gender) {

            int rank = getRank(year, name, gender);
            return getName(newYear, rank, gender);

        }

        private int findYear(String fName) {
            return Integer.parseInt(fName.substring(3, 7));

        }
        public int yearOfHighestRank(String name, String gender) {

            DirectoryResource dr = new DirectoryResource();
            int highestSoFar = -1;
            int yearOfRank = -1;
            String fileName;

            for (File file : dr.selectedFiles()) {

                String fName = file.getName();
                int year = findYear(fName);
                int rank = getRank(year, name, gender);


                // continue if rank not found in file
                if (rank == -1) continue;

                // for the first found rank
                if (highestSoFar == -1) {

                    highestSoFar = rank;
                    yearOfRank = year;
                }

                // for next ranks
                // check if new rank is higher(lower is higher) than current highest
                if (highestSoFar > rank) {
                    highestSoFar = rank;
                    yearOfRank = year;
                }


            }

            return yearOfRank;
        }

        public double getAverageRank(String name, String gender) {

            DirectoryResource dr = new DirectoryResource();
            int rankSum = 0;
            int rankCount = 0;

            for (File file : dr.selectedFiles()) {

                CSVParser parser = parserFactory(file);
                int rank = getRank(parser, name, gender);
                if (rank == - 1) continue;
                rankSum += rank;
                rankCount++;

            }

            if (rankCount == 0) return -1.0d;
            else return rankSum/(double)rankCount;

        }
        public int getTotalBirthsRankedHigher(int year, String name, String gender) {

            int nameRank = getRank(year, name, gender);
            CSVParser parser = parserFactory(year);
            int totalBirths = 0;
            for (CSVRecord record : parser) {
                // skip other gender
                if (!record.get(1).equals(gender)) continue;

                String recordName = record.get(0);
                int recordNameRank = getRank(year, recordName, gender);
                if (recordNameRank < nameRank) {
                    totalBirths += Integer.parseInt(record.get(2));
                }
                else break;
            }


            return totalBirths;

        }

    }

