package com.vupadhi.heyhelp.network.listener;



import com.vupadhi.heyhelp.dao.CountryDao;

import java.util.ArrayList;

public interface PassCountryIDs {

    void passCountry(ArrayList<CountryDao> countryDaos);
}
