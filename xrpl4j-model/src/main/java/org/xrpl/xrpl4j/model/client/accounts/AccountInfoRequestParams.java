package org.xrpl.xrpl4j.model.client.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.xrpl.xrpl4j.model.client.XrplRequestParams;
import org.xrpl.xrpl4j.model.client.common.LedgerIndex;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.Hash256;

import java.util.Optional;

/**
 * Request parameters for the "account_info" API method.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableAccountInfoRequestParams.class)
@JsonDeserialize(as = ImmutableAccountInfoRequestParams.class)
public interface AccountInfoRequestParams extends XrplRequestParams {

  static ImmutableAccountInfoRequestParams.Builder builder() {
    return ImmutableAccountInfoRequestParams.builder();
  }

  /**
   * Constructs an {@link AccountInfoRequestParams} for a specified account, with all other fields set to their
   * defaults.
   *
   * @param account The {@link Address} of the account.
   *
   * @return An {@link AccountInfoRequestParams} for the specified account.
   */
  static AccountInfoRequestParams of(Address account) {
    return builder()
        .account(account)
        .build();
  }

  /**
   * A unique {@link Address} for the account.
   *
   * @return The {@link Address} of the account.
   */
  Address account();

  /**
   * A {@link Hash256} for the ledger version to use.
   *
   * @return An optionally-present {@link Hash256} containing the ledger hash.
   */
  @JsonProperty("ledger_hash")
  Optional<Hash256> ledgerHash();

  /**
   * The ledger index of the ledger to use, or a shortcut string to choose a ledger automatically.
   *
   * @return A {@link LedgerIndex} denoting the ledger index to request.
   */
  @JsonProperty("ledger_index")
  @Value.Default
  default LedgerIndex ledgerIndex() {
    return LedgerIndex.CURRENT;
  }

  /**
   * A boolean indicating if the {@link #account()} field only accepts a public key or XRP Ledger {@link Address}.
   * Always true, as {@link #account()} is always an {@link Address}.
   *
   * @return {@code true} if the account field only accepts a public key or XRP Ledger address, otherwise {@code false}.
   *     Defaults to {@code true}.
   */
  @Value.Derived
  default boolean strict() {
    return true;
  }

  /**
   * If true, and the <a href="https://xrpl.org/known-amendments.html#feeescalation">FeeEscalation</a> amendment is
   * enabled, also returns stats about queued transactions associated with this account. Can only be used when
   * querying for the data from the current open ledger.
   *
   * @return {@code true} if queue transactions should be returned in the response, {@code false} if not.
   *     Defaults to {@code false}.
   */
  @Value.Default
  default boolean queue() {
    return false;
  }

  /**
   * If true, and the <a href="https://xrpl.org/known-amendments.html#multisign">MultiSign amendment</a> is enabled,
   * also returns any {@link org.xrpl.xrpl4j.model.ledger.SignerListObject}s associated with this account.
   *
   * @return {@code true} if signer lists should be returns, {@code false} if not. Defaults to {@code true}.
   */
  @Value.Default
  @JsonProperty("signer_lists")
  default boolean signerLists() {
    return true;
  }

}
