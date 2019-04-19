package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import paladinmod.powers.GuidingHandPower;

public class GuidingHandAction extends AbstractGameAction
{
    private AbstractPlayer player;
    private boolean cardsSelected;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PaladinMod:GuidingHandAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public GuidingHandAction()
    {
        this.player = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.cardsSelected = false;
    }

    @Override
    public void update()
    {
        if(!cardsSelected)
        {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, true);
            cardsSelected = true;
        }
        else
        {
            if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
            {
                AbstractCard selectedCard = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

                player.hand.addToTop(selectedCard);
                selectedCard.retain = true;

                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(player, player, new GuidingHandPower(player, selectedCard)));

                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                this.isDone = true;
            }
        }
    }
}
