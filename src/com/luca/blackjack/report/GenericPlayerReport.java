package com.luca.blackjack.report;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The solely purpose of this abstract class is to handle extended XML types
 * (defined in the XML stream by xsi:type) and map the right java bean to the
 * right XML object.
 * 
 * Every java bean that would normally implements the interface implemented by
 * this class must extend this class instead. Please do not define a new
 * {@link XmlRootElement} in any of the extended classes; instead, define the
 * name of the type by using the {@link XmlType} annotation.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */

@XmlRootElement(name = "player-report")
public abstract class GenericPlayerReport implements PlayerReport {

}
