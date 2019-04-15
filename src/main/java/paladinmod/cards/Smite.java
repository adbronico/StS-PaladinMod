package paladinmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.patches.PaladinTags;

public class Smite extends AbstractPaladinCard
{
    private static final String      ID                = "Smite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 4;
    private static final int         UPGRADE_DMG_ADD   = 3;
    private static final int         DIV_AMT           = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.BASIC;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public Smite()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.baseDamage = DMG_AMT;
        this.divinity = this.baseDivinity = DIV_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Smite();
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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
        // TODO: add DivinityPower gain
    }
}
