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
import com.megacrit.cardcrawl.powers.StrengthPower;
import paladinmod.PaladinMod;
import paladinmod.powers.DivinityPower;

public class DisarmingStrike extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:DisarmingStrike";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 5;
    private static final int         UPGRADE_DMG_ADD   = 2;
    private static final int         STR_LOW_AMT       = 1;
    private static final int         STR_LOW_ADD       = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public DisarmingStrike()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.baseMagicNumber = this.magicNumber = STR_LOW_AMT;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DisarmingStrike();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG_ADD);
            this.upgradeMagicNumber(STR_LOW_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        if(player.hasPower(DivinityPower.POWER_ID) && player.getPower(DivinityPower.POWER_ID).amount > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, player, new StrengthPower(monster, -this.magicNumber), -this.magicNumber));
        }
    }
}
