package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.DivinityPower;

public class ShieldOfFaith extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:ShieldOfFaith";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADE_CARD_DRAW = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public ShieldOfFaith()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.cardDraw = this.baseCardDraw = UPGRADE_CARD_DRAW;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ShieldOfFaith();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        int divinityAmount = player.hasPower(DivinityPower.POWER_ID) ? player.getPower(DivinityPower.POWER_ID).amount : 0;

        if(divinityAmount > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, divinityAmount));
        }
        if(this.upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
        }
    }
}
