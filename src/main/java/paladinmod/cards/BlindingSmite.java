package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import paladinmod.PaladinMod;
import paladinmod.patches.PaladinTags;

public class BlindingSmite extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:BlindingSmite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 5;
    private static final int         UPGRADE_DMG_ADD   = 2;
    private static final int         WEAK_AMT          = 1;
    private static final int         UPGRADE_WEAK_ADD  = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public BlindingSmite()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = WEAK_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new BlindingSmite();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG_ADD);
            this.upgradeMagicNumber(UPGRADE_WEAK_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, player, new WeakPower(monster, this.magicNumber, false), this.magicNumber));
    }
}
