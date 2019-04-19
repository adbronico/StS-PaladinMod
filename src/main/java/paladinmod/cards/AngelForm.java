package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.PlayerFlightPower;

public class AngelForm extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:AngelForm";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    public  static final String[]    EXTENDED_DESC     = cardStrings.EXTENDED_DESCRIPTION;
    private static final int         COST              = 3;
    private static final int         UPGRADED_COST     = 2;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public AngelForm()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
    }

    @Override
    public boolean canUse(AbstractPlayer player, AbstractMonster monster)
    {
        boolean canUse = super.canUse(player, monster);

        if(player.hasPower(PlayerFlightPower.POWER_ID))
        {
            canUse = false;
            this.cantUseMessage = UPGRADE_DESC;
        }

        return canUse;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new AngelForm();
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new PlayerFlightPower(player, 3)));
    }
}
