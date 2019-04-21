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
import paladinmod.powers.DivinityPower;

public class HellishRebuke extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:HellishRebuke";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADED_COST     = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public HellishRebuke()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new HellishRebuke();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if(player.hasPower(DivinityPower.POWER_ID) && player.getPower(DivinityPower.POWER_ID).amount < 0)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
                    new DamageInfo(player, -player.getPower(DivinityPower.POWER_ID).amount, this.damageTypeForTurn)));
        }
    }
}
