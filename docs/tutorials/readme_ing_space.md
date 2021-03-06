<p>This document introduces and describes the classes related to Ingredient search space. If you have any doubt or suggestion, please contact to the authors Matias Martinez and Martin Monperrus.</p>
<h1 id="introduction">Introduction</h1>
<p>A redundancy-based repair approach such as GenProg uses code written somewhere else in the application under repair to synthesize patches. We call <em>ingredient</em> to a piece of code (statement) used to synthesize a patch. The approach takes ingredients from an <em>ingredient search space</em>. As consequence, every patch generated by jGenProg is formed by ingredients taken from this space.</p>
<p>The current implementation of Astor works at the level of <em>statements</em>. That means that the ingredient space is composed of statements. Let’s continue this report by explaining how to extend the ingredient space is built and how jGenProg navigate it.</p>
<h2 id="ingredient-space-definition">Ingredient Space Definition</h2>
<p>jGenProg builds the ingredient search space by taking ingredients from the same application under repair. Astor is able to define three different ingredients spaces: A <em>Local</em> space include all ingredients from the files that contains at least one suspicious statement. A <em>Package</em> space contains all ingredients from the package that contains at least one suspicious statement. A <em>Global</em> contains all ingredient from the application.</p>
<p>Now, let us introduce the classes that represent the Ingredient Space.</p>
<p>Abstract class <span>IngredientSpace’</span> defines the interface of a ingredient space. It defines the methods:</p>
<p><span>defineSpace(ProgramVariant):</span> creates the search space for a specific variant.</p>
<p><span>getIngredients(L):</span> returns all the ingredients included in L.</p>
<p><span>getIngredients(L, T):</span> returns all ingredients of type T included in L.</p>
<p><span>setIngredients(L, List&lt;I&gt;):</span> puts a list of ingredient in L.</p>
<p><span>spaceScope():</span> returns the scope of the space (a constant).</p>
<p>Astor provides concretes ingredients spaces: <span>LocalIngredientSpace</span>, <span>PackageIngredientSpace</span> and <span>GlobalIngredientSpace</span>. All of those spaces extends from <span>AstorIngredientSpace</span> class, a class that provides an implemententation of<span>IngredientSpace</span> using Map structures.</p>
<p>If you want to create your own Ingredient space you can extends from ‘IngredientSpace’ (and to implement the data structures) or from AstorIngredientSpace and reuse the methods provided by this class.</p>
<p>Note that the responsibility of a IngredientSpace is to store ingredients and organize it according to the scope. For instance, the a local scope puts together all the ingredients that belongs to the same file, while the package scope puts all ingredients that belong to classes of the same package. As we present in next section, we delegate the navigation of this space to to classes that extend form ‘IngredientSearchStrategy’.</p>
<h2 id="ingredient-space-navigation">Ingredient Space Navigation</h2>
<p>Astor can navigate i.e., selects ingredients from the space, in several ways, for instance, in a randomly way. The class <span>IngredientSearchStrategy</span> defines a navigation strategy. It receives an IngredientSpace as parameter and defines the following methods:</p>
<p><span>getFixIngredient(ModificationPoint, AstorOperator):</span> returns a ingredient given a modification point and a operator. Note that later Astor will synthesize a patch that includes the returned ingredient. That patch applies the operator in the mentioned point.</p>
<p><span>getIngredientSpace():</span> returns the complete ingredient space.</p>
<p>We implement a concrete class <span>UniformRandomIngredientSearch</span>, that returns randomly an ingredient. Moreover, <span>EfficientIngredientStrategy</span> extends that class for avoid returning twice the same ingredient.</p>
<p>Finally, the canonical name of this strategy’s class is passed to Astor via the argument ’-ingredientstrategy’. By default, Astor uses EfficientIngredientStrategy.</p>
<h1 id="example-shortest-ingredient-strategy">Example: Shortest Ingredient Strategy</h1>
<p>In the current distribution we include as example a customized Ingredient Search space called <span>ShortestIngredientSearchStrategy</span>. This strategy returns the smallest ingredient of the space and removes it from the space. You can find two test cases at <span>IngredientSpaceTest</span> that use and assert this strategy.</p>
