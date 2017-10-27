package algrithm.bayes.dm13;

import algrithm.bayes.auxiliary.DataSet;
import algrithm.bayes.auxiliary.Evaluation;

/**
 *
 * @author daq
 */
public class TestAss3 {

    public static void main(String[] args) {
        String[] dataPaths = new String[]{"breast-cancer.data", "segment.data"};
        for (String path : dataPaths) {
            DataSet dataset = new DataSet(path);
//
//            // conduct 10-cv 
           Evaluation eva = new Evaluation(dataset, "NaiveBayes");
          eva.crossValidation();
//
//      // print mean and standard deviation of accuracy
      System.out.println("Dataset:" + path + ", mean and standard deviation of accuracy:" + eva.getAccMean() + "," + eva.getAccStd());
        }
    }
}
