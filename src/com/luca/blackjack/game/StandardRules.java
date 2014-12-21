package com.luca.blackjack.game;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

/**
 * The list of rules used in this blackjack sessions. Not all the rules can be
 * enabled at the same time. This class provides the user with some validations
 * to prevent rules to collide each other.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * @see Rules
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "standard-rules", propOrder = { "soft17", "surrender",
		"resplit", "resplitSplitAces", "hitSplitAces", "doubleSplitAces",
		"noDoubleAfterSplit", "renoRule", "renoRuleEuropean", "noHoleCard",
		"obo", "blackjackPayout", "dealerWinTies", "winPayout" })
public class StandardRules extends GenericRules {

	private Boolean soft17;
	private Boolean earlySurrender;
	private Boolean lateSurrender;
	private Boolean surrenderAllowed;
	private Integer resplit;
	private Boolean resplitSplitAces;
	private Boolean hitSplitAces;
	private Boolean doubleSplitAces;
	private Boolean noDoubleAfterSplit;
	private Boolean renoRule;
	private Boolean renoRuleEuropean;
	private Boolean noHoleCard;
	private Boolean obo;
	private String blackjackPayout;
	private Boolean dealerWinTies;
	private String winPayout;

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public StandardRules() {
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isSoft17()
	 */
	public boolean isSoft17() {
		return soft17;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setSoft17(boolean)
	 */
	@XmlElement(name = "soft-17")
	public void setSoft17(boolean soft17) {
		this.soft17 = soft17;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isEarlySurrender()
	 */
	public boolean isEarlySurrender() {
		return earlySurrender;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isLateSurrender()
	 */
	public boolean isLateSurrender() {
		return lateSurrender;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isSurrenderAllowed()
	 */
	public boolean isSurrenderAllowed() {
		return surrenderAllowed;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setSurrender(String)
	 */
	@XmlElement(name = "surrender")
	public void setSurrender(String surrender) {
		if (surrender == null)
			throw new IllegalArgumentException("Surrender type cannot be null");
		switch (surrender) {
		case "no-surrender":
			earlySurrender = false;
			lateSurrender = false;
			surrenderAllowed = false;
			break;
		case "early-surrender":
			earlySurrender = true;
			lateSurrender = false;
			surrenderAllowed = true;
			break;
		case "late-surrender":
			earlySurrender = false;
			lateSurrender = true;
			surrenderAllowed = true;
			break;
		default:
			throw new IllegalStateException("Surrender type not found");
		}
	}

	/**
	 * @see com.luca.blackjack.game.Rules#getResplit()
	 */
	public int getResplit() {
		return resplit;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setResplit(int)
	 */
	@XmlElement(name = "resplit")
	public void setResplit(int resplit) {
		if (resplit < -1)
			throw new IllegalArgumentException("Re-split value not recognised");
		this.resplit = resplit;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isResplitSplitAces()
	 */
	public boolean isResplitSplitAces() {
		return resplitSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setResplitSplitAces(boolean)
	 */
	@XmlElement(name = "resplit-split-aces")
	public void setResplitSplitAces(boolean resplitSplitAces) {
		this.resplitSplitAces = resplitSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isHitSplitAces()
	 */
	public boolean isHitSplitAces() {
		return hitSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setHitSplitAces(boolean)
	 */
	@XmlElement(name = "hit-split-aces")
	public void setHitSplitAces(boolean hitSplitAces) {
		this.hitSplitAces = hitSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isDoubleSplitAces()
	 */
	public boolean isDoubleSplitAces() {
		return doubleSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setDoubleSplitAces(boolean)
	 */
	@XmlElement(name = "double-split-aces")
	public void setDoubleSplitAces(boolean doubleSplitAces) {
		this.doubleSplitAces = doubleSplitAces;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isNoDoubleAfterSplit()
	 */
	public boolean isNoDoubleAfterSplit() {
		return noDoubleAfterSplit;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setNoDoubleAfterSplit(boolean)
	 */
	@XmlElement(name = "no-double-after-split")
	public void setNoDoubleAfterSplit(boolean noDoubleAfterSplit) {
		this.noDoubleAfterSplit = noDoubleAfterSplit;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isRenoRule()
	 */
	public boolean isRenoRule() {
		return renoRule;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setRenoRule(boolean)
	 */
	@XmlElement(name = "reno-rule")
	public void setRenoRule(boolean renoRule) {
		if (renoRuleEuropean != null && renoRule && renoRuleEuropean)
			throw new IllegalArgumentException(
					"Either reno rule or European reno rule allowed at the same time");
		this.renoRule = renoRule;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isRenoRuleEuropean()
	 */
	public boolean isRenoRuleEuropean() {
		return renoRuleEuropean;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setRenoRuleEuropean(boolean)
	 */
	@XmlElement(name = "reno-rule-european")
	public void setRenoRuleEuropean(boolean renoRuleEuropean) {
		if (renoRule != null && renoRule && renoRuleEuropean)
			throw new IllegalArgumentException(
					"Either reno rule or European reno rule allowed at the same time");
		this.renoRuleEuropean = renoRuleEuropean;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isNoHoleCard()
	 */
	public boolean isNoHoleCard() {
		return noHoleCard;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setNoHoleCard(boolean)
	 */
	@XmlElement(name = "no-hole-card")
	public void setNoHoleCard(boolean noHoleCard) {
		this.noHoleCard = noHoleCard;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isObo()
	 */
	public boolean isObo() {
		return obo;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setObo(boolean)
	 */
	@XmlElement(name = "obo")
	public void setObo(boolean obo) {
		this.obo = obo;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isDealerWinTies()
	 */
	public boolean isDealerWinTies() {
		return dealerWinTies;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setDealerWinTies(boolean)
	 */
	@XmlElement(name = "dealer-win-ties")
	public void setDealerWinTies(boolean dealerWinTies) {
		this.dealerWinTies = dealerWinTies;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#getBlackjackPayout()
	 */
	public String getBlackjackPayout() {
		return blackjackPayout;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#getBlackjackPayoutValue()
	 */
	public double getBlackjackPayoutValue() {
		String[] s = blackjackPayout.split(":");
		double n = new Double(s[0]);
		double d = new Double(s[1]);
		return n / d;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setBlackjackPayout(String)
	 */
	@XmlElement(name = "blackjack-payout")
	public void setBlackjackPayout(String blackjackPayout) {
		if (!blackjackPayout.matches("[0-9]+:[0-9]+"))
			throw new IllegalArgumentException(
					"I can't understand the win payout");
		this.blackjackPayout = blackjackPayout;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#getWinPayout()
	 */
	public String getWinPayout() {
		return winPayout;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#getWinPayoutValue()
	 */
	public double getWinPayoutValue() {
		String[] s = winPayout.split(":");
		double n = new Double(s[0]);
		double d = new Double(s[1]);
		return n / d;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#setWinPayout(String)
	 */
	@XmlElement(name = "win-payout")
	public void setWinPayout(String winPayout) {
		if (!winPayout.matches("[0-9]+:[0-9]+"))
			throw new IllegalArgumentException(
					"I can't understand the blackjack payout");
		this.winPayout = winPayout;
	}

	/**
	 * @see com.luca.blackjack.game.Rules#isInitialised()
	 */
	public boolean isInitialised() {
		if (soft17 == null)
			return false;
		if (earlySurrender == null)
			return false;
		if (lateSurrender == null)
			return false;
		if (surrenderAllowed == null)
			return false;
		if (resplit == null)
			return false;
		if (resplitSplitAces == null)
			return false;
		if (hitSplitAces == null)
			return false;
		if (doubleSplitAces == null)
			return false;
		if (noDoubleAfterSplit == null)
			return false;
		if (renoRule == null)
			return false;
		if (renoRuleEuropean == null)
			return false;
		if (noHoleCard == null)
			return false;
		if (obo == null)
			return false;
		if (blackjackPayout == null)
			return false;
		if (dealerWinTies == null)
			return false;
		if (winPayout == null)
			return false;
		return true;
	}

	@Override
	@NoLog
	public String toString() {
		return "StandardRules [soft17=" + soft17 + ", earlySurrender="
				+ earlySurrender + ", lateSurrender=" + lateSurrender
				+ ", noSurrenderAllowed=" + surrenderAllowed + ", resplit="
				+ resplit + ", resplitSplitAces=" + resplitSplitAces
				+ ", hitSplitAces=" + hitSplitAces + ", doubleSplitAces="
				+ doubleSplitAces + ", noDoubleAfterSplit="
				+ noDoubleAfterSplit + ", renoRule=" + renoRule
				+ ", renoRuleEuropean=" + renoRuleEuropean + ", noHoleCard="
				+ noHoleCard + ", obo=" + obo + ", blackjackPayout="
				+ blackjackPayout + ", dealerWinTies=" + dealerWinTies
				+ ", winPayout=" + winPayout + "]";
	}
}