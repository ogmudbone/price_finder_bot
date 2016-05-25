package pricefinder;

/**
 * Uses to save price element identity on same
 * domain between calls
 */
public interface DbAccessProviderInterface {

    /**
     * Calls to save identity data
     *
     * @param domain domain of online shop
     * @param identity string to identify price element of this domain
     *                 by some of types described below
     * @param identityType type of identity
     */
    void write(String domain, String identity, int identityType);

    /**
     * calls to remove db record
     * @param domain domain of online shop
     */
    void remove(String domain);

    /**
     * Calls to read identity string
     *
     * @param domain domain of online shop
     * @return identity of price element
     */
    String readIdentity(String domain);

    /**
     * Calls to read identity type
     *
     * @param domain domain of online shop
     * @return identity type
     */
    String readIdentityType(String domain);

}
