package paladinmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyStrengthAction;
import paladinmod.patches.PaladinTags;

public class StaggeringSmite extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:StaggeringSmite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/StaggeringSmite";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 5;
    private static final int         UPGRADE_DMG_ADD   = 3;
    private static final int         STR_LOSS_AMT      = 2;
    private static final int         STR_LOSS_ADD      = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public StaggeringSmite()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = STR_LOSS_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new StaggeringSmite();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG_ADD);
            this.upgradeMagicNumber(STR_LOSS_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ModifyStrengthAction(monster, -this.magicNumber));
        if(!monster.hasPower(ArtifactPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(monster, player, new GainStrengthPower(monster, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
