package com.transcore.connexion.sample.dob;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.axis2.AxisFault;

import cltool4j.GlobalConfigProperties;

import com.tcore.tcoreDobTypes.DobEvent;
import com.tcore.tcoreTypes.SessionToken;
import com.tcore.tcoreTypes.Warning;
import com.tcore.tfmiFreightMatching.LookupDobEventsOperation;
import com.tcore.tfmiFreightMatching.LookupDobEventsRequestDocument;
import com.tcore.tfmiFreightMatching.LookupDobEventsResponseDocument;
import com.tcore.tfmiFreightMatching.LookupDobEventsResult;
import com.tcore.tfmiFreightMatching.LookupDobEventsSuccessData;
import com.tcore.tfmiFreightMatching.TfmiFreightMatchingServiceStub;
import com.transcore.connexion.sample.BaseSampleClient;

public class LookupEvent extends BaseSampleClient {

    @Override
    protected void run() throws Exception {
        Calendar nextTime;

        // Read time to be read from permanent storage, otherwise start from the beginning of time.
        // Calendar sinceDate = Calendar.getInstance();
        // sinceDate.clear();
        Calendar sinceDate = getSinceDateFromProperty();
        int count = 0;
        while (true) {
            nextTime = Calendar.getInstance();
            nextTime.clear(Calendar.MILLISECOND); // Event times are only stored to the second.
            try {
                // Login
                final SessionToken sessionToken = loginDob();
                lookupDobEvents(sessionToken, sinceDate);
            } catch (final AxisFault fault) {
                System.out.println(fault.toString());
                // System.out.println(fault.getMessage() + " : " + fault.getDetail().getText());
                fault.printStackTrace();
            }
            sinceDate = nextTime;
            // Store the next time to be read in permanent storage.

            // Limit to 4 requests, for unit testing. Otherwise remove all references to count.
            count++;
            if (count > 4)
                break;

            // Sleep for 5 minutes between requests.
            try {
                Thread.sleep(300000);
            } catch (final InterruptedException e) {
                break;
            }
        }
    }

    protected Calendar getSinceDateFromProperty() throws RemoteException {

        final String sinceDateTxt = GlobalConfigProperties.singleton().getProperty(
                "dobCarrierExampleSinceDate");
        final String sinceDateTxtFormat = GlobalConfigProperties.singleton().getProperty(
                "dobCarrierExampleSinceDateFormat");

        if (sinceDateTxt != null && sinceDateTxtFormat != null) {
            try {
                final SimpleDateFormat formatter = new SimpleDateFormat(sinceDateTxtFormat);
                final Calendar sinceDate = Calendar.getInstance();
                sinceDate.setTime(formatter.parse(sinceDateTxt));
                return sinceDate;
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected synchronized void lookupDobEvents(final SessionToken sessionToken, final Calendar sinceDate)
            throws RemoteException {
        final LookupDobEventsRequestDocument lookupRequestDoc = LookupDobEventsRequestDocument.Factory
                .newInstance();

        final LookupDobEventsOperation operation = lookupRequestDoc.addNewLookupDobEventsRequest()
                .addNewLookupDobEventsOperations();

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        final String s = formatter.format(sinceDate.getTime());
        System.out.println("LookupEvents using sinceDate:" + s);

        operation.setSinceDate(sinceDate);
        validate(lookupRequestDoc);

        // Execute the lookup
        final TfmiFreightMatchingServiceStub stub = new TfmiFreightMatchingServiceStub(endpointUrl);
        final LookupDobEventsResponseDocument responseDoc = stub.lookupDobEvents(lookupRequestDoc, null,
                null, sessionHeaderDocument(sessionToken));
        final LookupDobEventsResult result = responseDoc.getLookupDobEventsResponse()
                .getLookupDobEventsResults();

        // Check for errors (note - some more severe errors will result in an AxisFault instead)
        if (!result.isSetLookupDobEventsSuccessData()) {
            System.out.println("Error message: " + result.getServiceError().getMessage() + " : "
                    + result.getServiceError().getDetailedMessage());
        } else {
            printLookupEventsResult(result.getLookupDobEventsSuccessData(), sessionToken);
        }

    }

    protected void printLookupEventsResult(final LookupDobEventsSuccessData successData,
            final SessionToken sessionToken) {

        System.out.println("LookupEventsResult------------------------------");
        if (successData != null) {
            if (successData.getDobEventsArray() != null) {
                for (int i = 0; i < successData.getDobEventsArray().length; ++i) {
                    final DobEvent de = successData.getDobEventsArray(i);

                    if (de.getCarrierId() != null) {
                        System.out.println("Carrier id:" + de.getCarrierId());
                        final LookupCarriers lookupCarriers = new LookupCarriers();
                        try {
                            lookupCarriers.lookupDOTCarrier(sessionToken, de.getCarrierId(), endpointUrl);
                        } catch (final Exception e) {
                            System.out.println("Exception when looking up carrier detail:" + e);
                        }
                    }

                    if (de.getEventTypesArray() != null) {
                        for (int j = 0; j < de.getEventTypesArray().length; ++j) {
                            System.out.println("Event Types:" + de.getEventTypesArray(j).toString());
                        }
                    }

                    if (de.getSignedContractsArray() != null) {
                        for (int k = 0; k < de.getSignedContractsArray().length; ++k) {
                            System.out
                                    .println("Signed Contracts:" + de.getSignedContractsArray(i).toString());
                        }
                    }
                }
            }
        }
        if (successData.getWarningsArray() != null) {
            for (int i = 0; i < successData.getWarningsArray().length; ++i) {
                final Warning w = successData.getWarningsArray(i);
                System.out.println("Warning code:" + w.getCode());
                System.out.println("Warning message:" + w.getMessage());
            }
        }
    }

    public static void main(final String[] args) {
        run(args);
    }

}
