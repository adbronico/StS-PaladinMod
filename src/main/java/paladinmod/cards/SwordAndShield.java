package paladinmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import paladinmod.PaladinMod;
import paladinmod.actions.SwordAndShieldAction;

public class SwordAndShield extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:SwordAndShield";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = -1;
    private static final int         DMG_BLOCK_AMT     = 4;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public SwordAndShield()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_BLOCK_AMT;
        this.baseBlock = DMG_BLOCK_AMT;
        this.baseMagicNumber = this.magicNumber = DMG_BLOCK_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new SwordAndShield();
    }

    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if (this.energyOnUse < EnergyPanel.totalCount)
        {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        AbstractDungeon.actionManager.addToBottom(
                new SwordAndShieldAction(player, monster, this.magicNumber, this.damageTypeForTurn, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }
}
