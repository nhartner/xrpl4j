package com.ripple.xrpl4j.transactions;

import java.util.Objects;

/**
 * A wrapper around a long value containing various XRPL Transaction Flags.
 *
 * @see "https://xrpl.org/transaction-common-fields.html#flags-field"
 */
public class Flags {

  public static final Flags UNSET = new Flags(0);

  private long value;

  private Flags(long value) {
    this.value = value;
  }

  public static Flags of(long value) {
    return new Flags(value);
  }

  public long getValue() {
    return value;
  }

  /**
   * Performs a bitwise OR on this {@link Flags} and another {@link Flags}
   * @param other The {@link Flags} to perform the OR with.
   * @return The {@link Flags} resulting from the OR operation.
   */
  public Flags bitwiseOr(Flags other) {
    return Flags.of(this.value | other.value);
  }

  /**
   * Performs a bitwise AND on this {@link Flags} and another {@link Flags}
   * @param other The {@link Flags} to perform the AND with.
   * @return The {@link Flags} resulting from the AND operation.
   */
  public Flags bitwiseAnd(Flags other) {
    return Flags.of(this.value & other.value);
  }

  /**
   * Determines if a specific transaction flag is set by performing a bitwise AND on this {@link Flags} and the
   * {@link Flags} in question, and checking if the result of that operation is equal to the given flag.
   *
   * @param flag The {@link Flags} that this method determines is set or not.
   * @return true if the flag is set, false if not.
   */
  public boolean isSet(Flags flag) {
    return this.bitwiseAnd(flag).equals(flag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    Flags flags = (Flags) o;
    return getValue() == flags.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  /**
   * A set of static Universal {@link Flags} which could apply to any transaction.
   */
  public static class Universal extends Flags {

    public static final Universal FULLY_CANONICAL_SIG = new Universal(0x80000000L);

    public static final Universal BITMASK = new Universal(0xff000000L);

    private Universal(long value) {
      super(value);
    }
  }

  /**
   * A set of static {@link Flags} which can be set on {@link com.ripple.xrpl4j.transactions.Payment} transactions.
   */
  public static class Payment extends Flags {

    public static final Universal NO_DIRECT_RIPPLE = new Universal(0x00010000L);
    public static final Universal PARTIAL_PAYMENT = new Universal(0x00020000L);
    public static final Universal LIMIT_QUALITY = new Universal(0x00040000L);
    public static final Universal BITMASK = new Universal(0x00ff0000);

    private Payment(long value) {
      super(value);
    }
  }
}