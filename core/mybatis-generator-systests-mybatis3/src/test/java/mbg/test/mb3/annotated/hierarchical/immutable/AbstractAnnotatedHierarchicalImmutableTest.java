package mbg.test.mb3.annotated.hierarchical.immutable;

import mbg.test.mb3.AbstractTest;

import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.FieldsblobsMapper;
import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.FieldsonlyMapper;
import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.PkblobsMapper;
import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.PkfieldsMapper;
import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.PkfieldsblobsMapper;
import mbg.test.mb3.generated.annotated.hierarchical.immutable.mapper.PkonlyMapper;

public abstract class AbstractAnnotatedHierarchicalImmutableTest extends AbstractTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sqlSessionFactory.getConfiguration().addMapper(FieldsblobsMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(FieldsonlyMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(PkblobsMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(PkfieldsblobsMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(PkfieldsMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(PkonlyMapper.class);
    }
    
    @Override
    public String getMyBatisConfigFile() {
        return "mbg/test/mb3/annotated/MapperConfig.xml";
    }
}
