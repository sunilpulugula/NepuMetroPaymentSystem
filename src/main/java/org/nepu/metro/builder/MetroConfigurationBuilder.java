package org.nepu.metro.builder;

import org.nepu.metro.config.MetroConfiguration;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 *
 * This class is used to build metro configuration from any source such as file, database, user provided ...etc
 **/
public interface MetroConfigurationBuilder {

    public MetroConfiguration build();

}
