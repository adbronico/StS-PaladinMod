package paladinmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.FlightPower;

public class PlayerFlightPower extends FlightPower
{
    public PlayerFlightPower(AbstractCreature owner, int amount)
    {
        super(owner, amount);
    }

    @Override
    public void onRemove()
    {
        // We do not want to be "grounded" like the Byrds are
    }
}
