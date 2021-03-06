/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchSetInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchSetInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in SwitchSetInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SwitchSetInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>switchSetInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SwitchSetInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SwitchSetInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new SwitchSetInject();
    }
    return INSTANCE;
  }
  
  private static SwitchSetInject INSTANCE;
  
  private SwitchSetInject() throws ViatraQueryException {
    querySpecifications.add(SwitchSetInjectQuerySpecification.instance());
  }
  
  public SwitchSetInjectQuerySpecification getSwitchSetInject() throws ViatraQueryException {
    return SwitchSetInjectQuerySpecification.instance();
  }
  
  public SwitchSetInjectMatcher getSwitchSetInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchSetInjectMatcher.on(engine);
  }
}
