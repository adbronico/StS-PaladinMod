package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import paladinmod.PaladinMod;

public class Meditate extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:Meditate";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DRAW_AMT          = 3;
    private static final int         UPGRADE_DRAW_AMT  = 1;
    private static final int         STR_LOSS_AMT      = 3;
    private static final int         STR_LOSS_ADD      = -1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public Meditate()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.cardDraw = this.baseCardDraw = DRAW_AMT;
        this.magicNumber = this.baseMagicNumber = STR_LOSS_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Meditate();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeCardDraw(UPGRADE_DRAW_AMT);
            this.upgradeMagicNumber(STR_LOSS_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new StrengthPower(player, -this.magicNumber), -this.magicNumber, true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new GainStrengthPower(player, this.magicNumber), this.magicNumber, true));
    }
}
