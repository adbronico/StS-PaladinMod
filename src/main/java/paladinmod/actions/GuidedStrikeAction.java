package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class GuidedStrikeAction extends AbstractGameAction
{
    private AbstractPlayer player;
    private boolean cardsSelected;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PaladinMod:GuidedStrikeAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public GuidedStrikeAction()
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
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
            cardsSelected = true;
        }
        else
        {
            if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
            {
                AbstractCard selectedCard = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

                player.hand.moveToDeck(selectedCard, false);
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                //AbstractDungeon.player.drawPile.refreshHandLayout();
                this.isDone = true;
            }
        }
    }
}
