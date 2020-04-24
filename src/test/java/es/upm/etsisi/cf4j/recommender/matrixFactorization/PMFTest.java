package es.upm.etsisi.cf4j.recommender.matrixFactorization;

import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.data.types.MockDataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PMFTest {

    final private static int seed = 69;
    final private static int numFactors = 2;
    final private static int numIters = 1;

    final private static int testUserId = 1;
    final private static int testItemId = 1;

    private static DataModel datamodel;

    @BeforeAll
    static void initAll() {
        datamodel = new DataModel(new MockDataSet());

    }

    @Test
    void pmfTest(){
        PMF pmf = new PMF(datamodel,numFactors,numIters,seed);
        pmf.fit();

        assertEquals(0.3360184913450531, pmf.predict(testUserId,testItemId));
        assertEquals(pmf.predict(testUserId,testItemId), pmf.predict(datamodel.getTestUser(testUserId))[testItemId]);

        assertEquals(pmf.getNumFactors(),numFactors);
        assertEquals(pmf.getNumIters(),numIters);
    }

}