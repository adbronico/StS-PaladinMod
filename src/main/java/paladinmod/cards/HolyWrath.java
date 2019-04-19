package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.ResetDivinityAction;
import paladinmod.powers.DivinityPower;

public class HolyWrath extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:HolyWrath";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         DMG_AMT           = 0;
    private static final int         DIV_BONUS_AMT     = 3;
    private static final int         BON_UPGRADE_AMT   = 2;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.BASIC;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public HolyWrath()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = DIV_BONUS_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new HolyWrath();
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster monster, float tmp)
    {
        int bonusDamage = 0;

        if(player.hasPower(DivinityPower.POWER_ID))
        {
            int divinityAmount = player.getPower(DivinityPower.POWER_ID).amount;
            if(divinityAmount > 0)
            {
                bonusDamage = magicNumber * divinityAmount;
            }
        }

        return tmp + bonusDamage;
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(BON_UPGRADE_AMT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ResetDivinityAction());
    }
}
