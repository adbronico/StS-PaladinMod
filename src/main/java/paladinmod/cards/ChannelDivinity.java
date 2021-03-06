package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.GainDivinityAction;

public class ChannelDivinity extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:ChannelDivinity";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 0;
    private static final int         DIV_AMOUNT        = 1;
    private static final int         DIV_UPGRADE_AMT   = 1;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public ChannelDivinity()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.divinity = this.baseDivinity = DIV_AMOUNT;
        this.cardDraw = this.baseCardDraw = CARD_DRAW_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ChannelDivinity();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDivinity(DIV_UPGRADE_AMT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new GainDivinityAction(this.divinity));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
    }
}
