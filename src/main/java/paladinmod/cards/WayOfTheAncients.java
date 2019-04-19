package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MalleablePower;
import paladinmod.PaladinMod;

public class WayOfTheAncients extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:WayOfTheAncients";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADED_COST     = 1;
    private static final CardType    TYPE              = AbstractCard.CardType.POWER;
    private static final CardRarity  RARITY            = AbstractCard.CardRarity.RARE;
    private static final CardTarget  TARGET            = AbstractCard.CardTarget.SELF;

    public WayOfTheAncients()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
    }

    @Override
    public boolean canUse(AbstractPlayer player, AbstractMonster monster)
    {
        boolean canUse = super.canUse(player, monster);

        if(player.hasPower(MalleablePower.POWER_ID))
        {
            canUse = false;
            this.cantUseMessage = UPGRADE_DESC;
        }

        return canUse;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new WayOfTheAncients();
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new MalleablePower(player)));
    }
}
