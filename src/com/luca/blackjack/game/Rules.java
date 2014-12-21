package com.luca.blackjack.game;

/**
 * This represents the list of rules used in this blackjack sessions.
 * <ul>
 * <li><b>Soft 17</b>: usually the dealer must hit soft 17 (H17). If
 * <code>soft17</code> is <code>true</code>, the dealer must stand-on-soft 17
 * (S17).
 * <li><b>Early surrender</b>: allows players to surrender before the dealer
 * checks the hole card.
 * <li><b>Resplit</b>: number of times a player is allowed to resplit. If 0, the
 * player is not allowed to resplit; if 1, the player is only allowed to resplit
 * once; if -1, the player is always allowed to resplit.
 * <li><b>Resplit split aces</b>: allows players to resplit an hand generated by
 * split aces.
 * <li><b>Hit split aces</b>: allows players to hit an hand generated by split
 * aces.
 * <li><b>Double split aces</b>: allows players to double an hand generated by
 * split aces.
 * <li><b>No double after split</b>: disallows players to double an hand
 * generated by split.
 * <li><b>Reno rule</b>: double is only permitted on hard totals of 9, 10 or 11.
 * <li><b>Reno rule European</b>: double is only permitted on hard totals of 10
 * or 11.
 * <li><b>No hole card</b>: the dealer's doesn't check the hole card until all
 * the players finish play.
 * <li><b>Original bet only</b>: under the <i>no hole card</i> rule, if the
 * dealer got blackjack, the player would only lose the original bet, and all
 * the other side bets would be pushed back.
 * <li><b>Blackjack payout</b>: blackjack pay - expressed in the notation X:X.
 * <li><b>Dealer wins ties</b>: rather than pushing, the player loses the games
 * when tying.
 * </ul>
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface Rules {

	/**
	 * @return the soft17
	 */
	public boolean isSoft17();

	/**
	 * @param soft17
	 *            the soft17 to set
	 */
	public void setSoft17(boolean soft17);

	/**
	 * @return the earlySurrender
	 */
	public boolean isEarlySurrender();

	/**
	 * @return the lateSurrender
	 */
	public boolean isLateSurrender();

	/**
	 * @return the surrenderAllowed
	 */
	public boolean isSurrenderAllowed();

	/**
	 * @param surrender
	 *            the surrender type to set
	 */
	public void setSurrender(String surrender);

	/**
	 * @return the resplit
	 */
	public int getResplit();

	/**
	 * @param resplit
	 *            the resplit to set
	 */
	public void setResplit(int resplit);

	/**
	 * @return the resplitSplitAces
	 */
	public boolean isResplitSplitAces();

	/**
	 * @param resplitSplitAces
	 *            the resplitSplitAces to set
	 */
	public void setResplitSplitAces(boolean resplitSplitAces);

	/**
	 * @return the hitSplitAces
	 */
	public boolean isHitSplitAces();

	/**
	 * @param hitSplitAces
	 *            the hitSplitAces to set
	 */
	public void setHitSplitAces(boolean hitSplitAces);

	/**
	 * @return the doubleSplitAces
	 */
	public boolean isDoubleSplitAces();

	/**
	 * @param doubleSplitAces
	 *            the doubleSplitAces to set
	 */
	public void setDoubleSplitAces(boolean doubleSplitAces);

	/**
	 * @return the noDoubleAfterSplit
	 */
	public boolean isNoDoubleAfterSplit();

	/**
	 * @param noDoubleAfterSplit
	 *            the noDoubleAfterSplit to set
	 */
	public void setNoDoubleAfterSplit(boolean noDoubleAfterSplit);

	/**
	 * @return the renoRule
	 */
	public boolean isRenoRule();

	/**
	 * @param renoRule
	 *            the renoRule to set
	 */
	public void setRenoRule(boolean renoRule);

	/**
	 * @return the renoRuleEuropean
	 */
	public boolean isRenoRuleEuropean();

	/**
	 * @param renoRuleEuropean
	 *            the renoRuleEuropean to set
	 */
	public void setRenoRuleEuropean(boolean renoRuleEuropean);

	/**
	 * @return the noHoleCard
	 */
	public boolean isNoHoleCard();

	/**
	 * @param noHoleCard
	 *            the noHoleCard to set
	 */
	public void setNoHoleCard(boolean noHoleCard);

	/**
	 * @return the obo
	 */
	public boolean isObo();

	/**
	 * @param obo
	 *            the obo to set
	 */
	public void setObo(boolean obo);

	/**
	 * @return the dealerWinTies
	 */
	public boolean isDealerWinTies();

	/**
	 * @param dealerWinTies
	 *            the dealerWinTies to set
	 */
	public void setDealerWinTies(boolean dealerWinTies);

	/**
	 * @return the blackjackPayout
	 */
	public double getBlackjackPayoutValue();

	/**
	 * @return the blackjackPayout
	 */
	public String getBlackjackPayout();

	/**
	 * @param blackjackPayout
	 *            the blackjackPayout to set
	 */
	public void setBlackjackPayout(String blackjackPayout);

	/**
	 * @return the winPayout
	 */
	public double getWinPayoutValue();

	/**
	 * @return the winPayout
	 */
	public String getWinPayout();

	/**
	 * @param winPayout
	 *            the winPayout to set
	 */
	public void setWinPayout(String winPayout);

	/**
	 * Checks whether all the instance variables of this object have been
	 * initialised.
	 * 
	 * @return <code>true</code> if all the instance variables have been
	 *         initialised, <code>false</code> otherwise
	 */
	public boolean isInitialised();
}