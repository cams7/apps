/**
 * 
 */
package cams7.apps.jee.service;

import java.io.Serializable;

import cams7.apps.jee.jpa.repository.BaseRepository;
import cams7.apps.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
