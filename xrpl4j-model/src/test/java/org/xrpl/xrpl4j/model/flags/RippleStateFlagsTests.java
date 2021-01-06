package org.xrpl.xrpl4j.model.flags;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class RippleStateFlagsTests extends AbstractFlagsTest {

  boolean lsfLowReserve;
  boolean lsfHighReserve;
  boolean lsfLowAuth;
  boolean lsfHighAuth;
  boolean lsfLowNoRipple;
  boolean lsfHighNoRipple;
  boolean lsfLowFreeze;
  boolean lsfHighFreeze;

  long expectedFlags;


  /**
   * Required-args constructor.
   *
   * @param lsfLowReserve   The current value of {@link this.lsfLowReserve}.
   * @param lsfHighReserve  The current value of {@link this.lsfHighReserve}.
   * @param lsfLowAuth      The current value of {@link this.lsfLowAuth}.
   * @param lsfHighAuth     The current value of {@link this.lsfHighAuth}.
   * @param lsfLowNoRipple  The current value of {@link this.lsfLowNoRipple}.
   * @param lsfHighNoRipple The current value of {@link this.lsfHighNoRipple}.
   * @param lsfLowFreeze    The current value of {@link this.lsfLowFreeze}.
   * @param lsfHighFreeze   The current value of {@link this.lsfHighFreeze}.
   */
  public RippleStateFlagsTests(
      boolean lsfLowReserve,
      boolean lsfHighReserve,
      boolean lsfLowAuth,
      boolean lsfHighAuth,
      boolean lsfLowNoRipple,
      boolean lsfHighNoRipple,
      boolean lsfLowFreeze,
      boolean lsfHighFreeze
  ) {
    this.lsfLowReserve = lsfLowReserve;
    this.lsfHighReserve = lsfHighReserve;
    this.lsfLowAuth = lsfLowAuth;
    this.lsfHighAuth = lsfHighAuth;
    this.lsfLowNoRipple = lsfLowNoRipple;
    this.lsfHighNoRipple = lsfHighNoRipple;
    this.lsfLowFreeze = lsfLowFreeze;
    this.lsfHighFreeze = lsfHighFreeze;

    expectedFlags = (lsfLowReserve ? Flags.RippleStateFlags.LOW_RESERVE.getValue() : 0L) |
        (lsfHighReserve ? Flags.RippleStateFlags.HIGH_RESERVE.getValue() : 0L) |
        (lsfLowAuth ? Flags.RippleStateFlags.LOW_AUTH.getValue() : 0L) |
        (lsfHighAuth ? Flags.RippleStateFlags.HIGH_AUTH.getValue() : 0L) |
        (lsfLowNoRipple ? Flags.RippleStateFlags.LOW_NO_RIPPLE.getValue() : 0L) |
        (lsfHighNoRipple ? Flags.RippleStateFlags.HIGH_NO_RIPPLE.getValue() : 0L) |
        (lsfLowFreeze ? Flags.RippleStateFlags.LOW_FREEZE.getValue() : 0L) |
        (lsfHighFreeze ? Flags.RippleStateFlags.HIGH_FREEZE.getValue() : 0L);
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return getBooleanCombinations(8);
  }

  @Test
  public void testDeriveIndividualFlagsFromFlags() {
    Flags.RippleStateFlags flags = Flags.RippleStateFlags.of(expectedFlags);

    assertThat(flags.getValue()).isEqualTo(expectedFlags);
    assertThat(flags.lsfLowReserve()).isEqualTo(lsfLowReserve);
    assertThat(flags.lsfHighReserve()).isEqualTo(lsfHighReserve);
    assertThat(flags.lsfLowAuth()).isEqualTo(lsfLowAuth);
    assertThat(flags.lsfHighAuth()).isEqualTo(lsfHighAuth);
    assertThat(flags.lsfLowNoRipple()).isEqualTo(lsfLowNoRipple);
    assertThat(flags.lsfHighNoRipple()).isEqualTo(lsfHighNoRipple);
    assertThat(flags.lsfLowFreeze()).isEqualTo(lsfLowFreeze);
    assertThat(flags.lsfHighFreeze()).isEqualTo(lsfHighFreeze);
  }
}
