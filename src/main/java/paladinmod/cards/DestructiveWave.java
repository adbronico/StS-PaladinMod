package paladinmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import paladinmod.PaladinMod;

public class DestructiveWave extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:DestructiveWave";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 6;
    private static final int         UPGRADE_DMG_ADD   = 3;
    private static final int         WEAK_AMT          = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ALL_ENEMY;

    public DestructiveWave()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = WEAK_AMT;
        this.isMultiDamage = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DestructiveWave();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for(AbstractMonster herman : AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(herman, player, new WeakPower(herman, this.magicNumber, false), this.magicNumber));
        }
    }
}
