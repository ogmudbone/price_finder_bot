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
     *                     could be XPATH, ID or CLASS
     */
    void write(String domain, String identity, int identityType);

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
     * @return identity types of price element could be XPATH, ID or CLASS
     */
    String readIdentityType(String domain);

}
