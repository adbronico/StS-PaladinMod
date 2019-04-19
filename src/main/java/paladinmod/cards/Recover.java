package paladinmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import paladinmod.PaladinMod;
import paladinmod.actions.RecoverAction;

public class Recover extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:Recover";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = -1;
    private static final int         HEAL_AMT          = 2;
    private static final int         UPGRADE_HEAL_ADD  = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public Recover()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseMagicNumber = this.magicNumber = this.misc = HEAL_AMT;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Recover();
    }

    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_HEAL_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if (this.energyOnUse < EnergyPanel.totalCount)
        {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        AbstractDungeon.actionManager.addToBottom(new RecoverAction(player, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
    }
}
