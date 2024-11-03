package com.fakeBankDetails.fakeBank.utils;

import com.fakeBankDetails.fakeBank.enums.IndianState;

import java.util.HashMap;
import java.util.Map;

public class IndianStateMappingCode {

    public static final Map<IndianState, String> stateCode;

    static {
        stateCode = new HashMap<>();
        stateCode.put(IndianState.ANDHRA_PRADESH, "0001");
        stateCode.put(IndianState.ARUNACHAL_PRADESH, "0002");
        stateCode.put(IndianState.ASSAM, "0003");
        stateCode.put(IndianState.BIHAR, "0004");
        stateCode.put(IndianState.CHHATTISGARH, "0005");
        stateCode.put(IndianState.GOA, "0006");
        stateCode.put(IndianState.GUJARAT, "0007");
        stateCode.put(IndianState.HARYANA, "0008");
        stateCode.put(IndianState.HIMACHAL_PRADESH, "0009");
        stateCode.put(IndianState.JHARKHAND, "0010");
        stateCode.put(IndianState.KARNATAKA, "0011");
        stateCode.put(IndianState.KERALA, "0012");
        stateCode.put(IndianState.MADHYA_PRADESH, "0013");
        stateCode.put(IndianState.MAHARASHTRA, "0014");
        stateCode.put(IndianState.MANIPUR, "0015");
        stateCode.put(IndianState.MEGHALAYA, "0016");
        stateCode.put(IndianState.MIZORAM, "0017");
        stateCode.put(IndianState.NAGALAND, "0018");
        stateCode.put(IndianState.ODISHA, "0019");
        stateCode.put(IndianState.PUNJAB, "0020");
        stateCode.put(IndianState.RAJASTHAN, "0021");
        stateCode.put(IndianState.SIKKIM, "0022");
        stateCode.put(IndianState.TAMIL_NADU, "0023");
        stateCode.put(IndianState.TELANGANA, "0024");
        stateCode.put(IndianState.TRIPURA, "0025");
        stateCode.put(IndianState.UTTAR_PRADESH, "0026");
        stateCode.put(IndianState.UTTARAKHAND, "0027");
        stateCode.put(IndianState.WEST_BENGAL, "0028");

        // Union Territories
        stateCode.put(IndianState.ANDAMAN_AND_NICOBAR_ISLANDS, "0029");
        stateCode.put(IndianState.CHANDIGARH, "0030");
        stateCode.put(IndianState.DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU, "0031");
        stateCode.put(IndianState.LAKSHADWEEP, "0032");
        stateCode.put(IndianState.DELHI, "0033");
        stateCode.put(IndianState.PUDUCHERRY, "0034");
        stateCode.put(IndianState.LADAKH, "0035");
        stateCode.put(IndianState.JAMMU_AND_KASHMIR, "0036");
    }

    public static String getStateCode(IndianState state){
        return stateCode.get(state);
    }
}
